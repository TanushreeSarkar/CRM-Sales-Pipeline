import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate, useParams, useLocation } from 'react-router-dom';
import { Container, Typography, AppBar, Toolbar } from '@mui/material';
import ShortenerForm from './components/ShortenerForm';
import ResultsList from './components/ResultsList';
import StatsItem from './components/StatsItem';
import Log from './utils/loggingMiddleware';

const RedirectComponent: React.FC = () => {
  const { shortcode } = useParams<{ shortcode: string }>();
  const location = useLocation();
  const [shortenedUrls] = useState<any[]>(JSON.parse(localStorage.getItem('shortenedUrls') || '[]'));

  const url = shortenedUrls.find(u => u.shortcode === shortcode);
  useEffect(() => {
    if (url) {
      if (Date.now() > url.expiry) {
        Log({ stack: 'App.RedirectComponent', level: 'warn', package: 'app', message: `Expired link: ${shortcode}` });
      } else {
        url.clicks += 1;
        url.clickData.push({
          timestamp: new Date().toISOString(),
          source: document.referrer || 'Direct',
          geo: navigator.geolocation ? 'Simulated Location' : 'Unknown',
        });
        localStorage.setItem('shortenedUrls', JSON.stringify(shortenedUrls));
        Log({ stack: 'App.RedirectComponent', level: 'info', package: 'app', message: `Redirecting to ${url.original}` });
        window.location.href = url.original;
      }
    } else {
      Log({ stack: 'App.RedirectComponent', level: 'error', package: 'app', message: `Invalid shortcode: ${shortcode}` });
    }
  }, [shortcode, url, shortenedUrls]);

  if (!url) return <Typography color="error">Invalid shortcode</Typography>;
  if (Date.now() > url.expiry) return <Typography color="error">Link expired</Typography>;
  return null; // Redirect handled by window.location
};

const AppContent: React.FC = () => {
  const [shortenedUrls, setShortenedUrls] = useState<any[]>(JSON.parse(localStorage.getItem('shortenedUrls') || '[]'));

  useEffect(() => {
    Log({ stack: 'App.useEffect', level: 'info', package: 'app', message: 'App initialized' });
    localStorage.setItem('shortenedUrls', JSON.stringify(shortenedUrls));
  }, [shortenedUrls]);

  const handleShorten = (results: any[]) => {
    const updatedUrls = [...shortenedUrls, ...results];
    setShortenedUrls(updatedUrls);
    Log({ stack: 'App.handleShorten', level: 'info', package: 'app', message: 'URLs shortened' });
  };

  return (
    <Container>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6">AffordMed URL Shortener</Typography>
        </Toolbar>
      </AppBar>
      <Routes>
        <Route path="/" element={
          <>
            <ShortenerForm onShorten={handleShorten} />
            {shortenedUrls.length > 0 && <ResultsList results={shortenedUrls} />}
          </>
        } />
        <Route path="/stats" element={
          shortenedUrls.length > 0 ? (
            shortenedUrls.map((url, index) => <StatsItem key={index} url={url} />)
          ) : (
            <Typography>No shortened URLs yet</Typography>
          )
        } />
        <Route path="/:shortcode" element={<RedirectComponent />} />
      </Routes>
    </Container>
  );
};

const App: React.FC = () => (
  <Router>
    <AppContent />
  </Router>
);

export default App;
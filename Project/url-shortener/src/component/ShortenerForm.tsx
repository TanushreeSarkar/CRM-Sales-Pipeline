import React, { useState } from 'react';
import { TextField, Button, Alert, Grid } from '@mui/material';
import Log from '../utils/loggingMiddleware';
import shortid from 'shortid';

interface URLInput {
  original: string;
  validity?: number;
  shortcode?: string;
}

const ShortenerForm: React.FC<{ onShorten: (results: any[]) => void }> = ({ onShorten }) => {
  const [urls, setUrls] = useState<URLInput[]>([{ original: '' }]);
  const [error, setError] = useState<string | null>(null);

  const validateInput = (input: URLInput): string | null => {
    try {
      new URL(input.original);
    } catch {
      Log({ stack: 'ShortenerForm.validate', level: 'error', package: 'shortener', message: `Invalid URL: ${input.original}` });
      return 'Invalid URL format';
    }
    if (input.validity && (!Number.isInteger(input.validity) || input.validity <= 0)) {
      Log({ stack: 'ShortenerForm.validate', level: 'error', package: 'shortener', message: `Invalid validity: ${input.validity}` });
      return 'Validity must be a positive integer';
    }
    if (input.shortcode && !/^[a-zA-Z0-9]{4,12}$/.test(input.shortcode)) {
      Log({ stack: 'ShortenerForm.validate', level: 'error', package: 'shortener', message: `Invalid shortcode: ${input.shortcode}` });
      return 'Shortcode must be 4-12 alphanumeric characters';
    }
    return null;
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);
    if (urls.length > 5) {
      Log({ stack: 'ShortenerForm.submit', level: 'warn', package: 'shortener', message: 'Exceeded 5 URL limit' });
      setError('Maximum 5 URLs allowed');
      return;
    }

    const validationErrors = urls.map(validateInput).filter(err => err);
    if (validationErrors.length) {
      setError(validationErrors[0]);
      return;
    }

    Log({ stack: 'ShortenerForm.submit', level: 'info', package: 'shortener', message: 'Submitting URLs' });
    const results = urls.map(url => {
      const shortcode = url.shortcode || shortid.generate();
      const expiry = url.validity ? Date.now() + url.validity * 60000 : Date.now() + 30 * 60000;
      Log({ stack: 'ShortenerForm.shorten', level: 'info', package: 'shortener', message: `Shortening ${url.original} to ${shortcode}` });
      return { original: url.original, shortcode, expiry, fullUrl: `http://localhost:3000/${shortcode}`, clicks: 0, clickData: [] };
    });
    onShorten(results);
  };

  const addUrl = () => setUrls([...urls, { original: '' }]);
  const updateUrl = (index: number, field: keyof URLInput, value: string) => {
    const newUrls = [...urls];
    newUrls[index] = { ...newUrls[index], [field]: field === 'validity' ? parseInt(value) || undefined : value };
    setUrls(newUrls);
  };

  return (
    <form onSubmit={handleSubmit}>
      {urls.map((url, index) => (
        <Grid container spacing={2} key={index} sx={{ mb: 2 }}>
          <Grid item xs={12} sm={6}>
            <TextField
              fullWidth
              label="Original URL"
              value={url.original}
              onChange={(e) => updateUrl(index, 'original', e.target.value)}
              required
            />
          </Grid>
          <Grid item xs={6} sm={3}>
            <TextField
              fullWidth
              label="Validity (minutes)"
              type="number"
              value={url.validity || ''}
              onChange={(e) => updateUrl(index, 'validity', e.target.value)}
            />
          </Grid>
          <Grid item xs={6} sm={3}>
            <TextField
              fullWidth
              label="Custom Shortcode"
              value={url.shortcode || ''}
              onChange={(e) => updateUrl(index, 'shortcode', e.target.value)}
            />
          </Grid>
        </Grid>
      ))}
      {error && <Alert severity="error">{error}</Alert>}
      <Button variant="contained" onClick={addUrl} sx={{ mr: 2 }}>Add URL</Button>
      <Button variant="contained" type="submit">Shorten URLs</Button>
    </form>
  );
};

export default ShortenerForm;
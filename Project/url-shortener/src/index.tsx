import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import Log from './utils/loggingMiddleware';

Log({ stack: 'index.useEffect', level: 'info', package: 'app', message: 'Application started' });

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);
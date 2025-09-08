import React from 'react';
import { List, ListItem, ListItemText, Typography } from '@mui/material';
import Log from '../utils/loggingMiddleware';

const ResultsList: React.FC<{ results: any[] }> = ({ results }) => {
  React.useEffect(() => {
    Log({ stack: 'ResultsList.useEffect', level: 'info', package: 'shortener', message: 'Displaying results' });
  }, [results]);

  return (
    <List>
      {results.map((result, index) => (
        <ListItem key={index}>
          <ListItemText
            primary={`Shortened URL: ${result.fullUrl}`}
            secondary={`Original: ${result.original}, Expires: ${new Date(result.expiry).toLocaleString()}`}
          />
        </ListItem>
      ))}
    </List>
  );
};

export default ResultsList;
import React, { useState, useEffect } from 'react';
import { ListItem, ListItemText, Collapse, List, Typography } from '@mui/material';
import Log from '../utils/loggingMiddleware';

interface ClickData {
  timestamp: string;
  source: string;
  geo: string;
}

interface URLData {
  fullUrl: string;
  shortcode: string;
  expiry: number;
  clicks: number;
  clickData: ClickData[];
  validity?: number;
  original?: string;
}

const StatsItem: React.FC<{ url: URLData }> = ({ url }) => {
  const [open, setOpen] = useState(false);

  useEffect(() => {
    Log({ stack: 'StatsItem.useEffect', level: 'info', package: 'stats', message: `Loading stats for ${url.shortcode}` });
  }, [url]);

  const handleClick = () => {
    setOpen(!open);
    Log({ stack: 'StatsItem.handleClick', level: 'info', package: 'stats', message: `Toggling details for ${url.shortcode}` });
  };

  return (
    <>
      <ListItem button onClick={handleClick}>
        <ListItemText
          primary={`Shortened: ${url.fullUrl}`}
          secondary={`Clicks: ${url.clicks}, Created: ${new Date(url.expiry - (url.validity || 30) * 60000).toLocaleString()}, Expires: ${new Date(url.expiry).toLocaleString()}`}
        />
      </ListItem>
      <Collapse in={open} timeout="auto" unmountOnExit>
        <List component="div" disablePadding>
          {url.clickData.map((click, idx) => (
            <ListItem key={idx} sx={{ pl: 4 }}>
              <ListItemText
                primary={`Click ${idx + 1}`}
                secondary={`Time: ${click.timestamp}, Source: ${click.source}, Location: ${click.geo}`}
              />
            </ListItem>
          ))}
        </List>
      </Collapse>
    </>
  );
};

export default StatsItem;
# URL Shortener Web App for AffordMed

## Setup
1. Clone the repository.
2. Run `npm install` to install dependencies.
3. Run `npm start` to start the app on `http://localhost:3000`.

## Assumptions
- Backend API endpoints (e.g., `POST /shorten`, `GET /stats`) are mocked; replace with actual URLs from Pre-Test Setup.
- Geo location is simulated as 'Simulated Location' due to no backend integration.
- Logging Middleware sends to a mock Test Server (`https://test-server.com/log`); update with real endpoint.

## Instructions Followed
- Built with React, running on `localhost:3000`.
- Integrated Logging Middleware for all events (no console logging).
- Pre-authorized environment (no auth).
- Unique shortcodes managed client-side with `shortid`.
- Default 30-minute validity; validated inputs.
- Custom shortcodes (4-12 alphanumeric) with validation.
- React Router for routing and redirection.
- Two pages: `/` (Shortener) and `/stats` (Statistics).
- Material UI for styling.
- Client-side validation and error handling.
- LocalStorage for session persistence.
- Clean UX with highlighted elements.
- Edge cases (invalid inputs, expiry, 1-5 URLs) handled.
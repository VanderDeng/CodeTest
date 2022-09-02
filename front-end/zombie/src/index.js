import React, { StrictMode } from 'react';
import * as ReactDOM from 'react-dom/client';
import Zombie from './pages/Zombie';

import reportWebVitals from './reportWebVitals';
import * as serviceWorker from './serviceWorker';

const container = document.getElementById('root');
const root = ReactDOM.createRoot(container);

root.render(
  <StrictMode>
    <Zombie />
  </StrictMode>
);

serviceWorker.unregister();
reportWebVitals();

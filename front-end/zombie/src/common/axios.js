import axios from 'axios';

const customAxios = axios.create({
  baseURL: 'http://localhost:8080',
  timeout: 2000,
});

customAxios.interceptors.response.use(
  res => {
    return res.data;
  },
  err => {
    return Promise.resolve(err);
  }
);

customAxios.CancelToken = axios.CancelToken;

export default customAxios;

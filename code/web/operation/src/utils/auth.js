const TokenKey = 'token'

export function getToken() {
  return JSON.parse(localStorage.getItem(TokenKey));
}

export function setToken(data) {
  return localStorage.setItem(TokenKey, JSON.stringify(data));
}

export function removeToken() {
  return localStorage.removeItem(TokenKey);
}

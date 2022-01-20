FROM nginx:1.21.5-alpine

RUN ln -sf /dev/stdout /var/log/nginx/react-access.log && ln -sf /dev/stderr /var/log/nginx/react-error.log

COPY ./build/ /usr/share/nginx/web/
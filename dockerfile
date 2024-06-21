# 使用 Node.js 作为构建环境
FROM node:20.11.1 AS builder

# 设置工作目录
WORKDIR /app

# 复制 package.json 和 package-lock.json 并安装依赖
COPY package.json package-lock.json ./
RUN npm install

# 复制项目文件并构建
COPY . .
RUN npm run build

# 使用 Nginx 作为生产环境的服务器
FROM nginx:stable
COPY --from=builder /app/dist /usr/share/nginx/html

# 暴露端口
EXPOSE 80

# 启动 Nginx
CMD ["nginx", "-g", "daemon off;"]

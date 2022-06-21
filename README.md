# Demo Project

# prepare
```bash
sed -i 's|http://gitea.cluster.com/czyhome|https://github.com/czy21|g' .gitmodules
git submodule update --recursive --init
```
## 工程结构
``` 
DEMO
├── code
├    ├── api -- SpringBoot后端API
|    |
├    ├── gradle-plugin -- Gradle发布构建插件
|    |
├    ├── web/react -- 前端工程
|    |
├── db
├    ├── mysql -- mysql初始化脚本 
|    |
├    ├── neo4j -- 图数据库初始化脚本
|    |
├── shell -- 构建、打包、发布 By 环境 (Py Shell)
|    |
├    ├── template -- by环境构建配置文件(包括gradle,dockerfile,pipeline,nginx)
|    |
├    ├── local -- 本地构建
|    |
├    ├── play  -- Linux(CentOS_8)下构建并结合Jenkins作为主要发布流程工具
```

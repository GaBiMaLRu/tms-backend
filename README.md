# 启动说明
1. 单机模式启动Nacos`` nacos/bin/startup.cmd -m standalone ``
2. 创建 Nacos 命名空间
3. 将 `/assets/nacos_config_export*.zip` 导入 Nacos 对应的命名空间下
4. 修改导入后的配置文件，需要注意修改的是 tms.mysql.database属性
5. 创建数据库后手动执行`database/`下的 sql 脚本。
6. 启动`auth-server、auth-gateway、base、dispatch、druid、netty、oms、user、web-manager、work`下的Application类
7. 类启动参数 `-Dnacos.host=192.168.11.1:8848 -Dnacos.namespace=7d4b5a32-3c74-4617-adc4-c610bf27a82f`，`nacos.host`是 Nacos 服务地址和端口号，`nacos.namespace`是创建的命名空间的编码
8. 前端文件配置在`.env.development`的`VUE_APP_DEV_REQUEST_DOMAIN_PREFIX`属性，本机启动可以不做修改
9. 管理员登录账密 tms/123456
10. 前端项目可以直接拉取，也可以通过 submodule 的方式拉取
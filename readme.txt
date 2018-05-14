目标：旨在构建一个简单、逻辑清晰的后台服务框架
框架：spring+mybatis
结构：controller项目、service项目、dao项目、model项目、utils项目
      每一个项目有一个子pom文件、同时共享一个全局pom文件
      controller构成：main函数、restful Controller、配置文件
      service构成：面向接口的service服务
      dao构成：mybatis接口与xml文件、多数据源切换配置
      model构成：po直接对应数据表、dto存放request和response、bo存放一些枚举、静态变量等
      引用层级controller引用servie，servie引用dao，service通过引用其他service完成复杂业务，model与untils为controller、servie、dao共享
      但是为保持结构清晰，model的po只能被dao引用；

01：mybatis多数据源支持（待调试）
02：redis缓存支持
03：http封装支持（bug）
04：swagger支持（bug）


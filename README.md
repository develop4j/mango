此项目是一个权限管理系统，整体分为前端和后端两大部分

1.安装sass-loader后报错：this.getResolve is not a function：
解决方法：
	1.npm uninstall sass-loader //卸载sass-loader
	2.npm install sass-loader@7.3.1 --save-dev //安装7.3.1版本的scss，然后启动项目就ok了
2.命令解析：
	npm audit ： npm@5.10.0 & npm@6，允许开发人员分析复杂的代码，并查明特定的漏洞和缺陷。

	npm audit fix ：npm@6.1.0,  检测项目依赖中的漏洞并自动安装需要更新的有漏洞的依赖，而不必再自己进行跟踪和修复。
3.搭建mango-hystrix是访问页面报错：Unable to connect to Command Metric Stream. 后台报错：Failed opening connection to http://localhost:8005/hystrix.stream : 404 : HTTP/1.1 404 
解决方法：
	需在应用程序启动类注入HystrixMetricsStreamServlet：
	@Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        List mappingList = new ArrayList();
        mappingList.add("/hystrix.stream");
        registrationBean.setUrlMappings(mappingList);
		//registrationBean.addUrlMappings("/hystrix.stream"); 或者使添加此参数
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;

    }

4.搭建mango-config报错;If you are using the git profile, you need to set a Git URI in your configuration.  If you are using a native profile and have spring.cloud.config.server.bootstrap=true, you need to use a composite configuration.
解决方法：在此模块的application.yml中添加:spring.profiles.active=native

5.Consul service ids must not be empty, must start with a letter, end with a letter or digit, and have
解决方法：serviceName: ${spring.application.name} 改为：mango-consumer	# 注册到consul的服务名称

# Spring Boot Web开发
Web开发时开发中至关重要的一部分，Web开发的核心内容主要包括内嵌Servlet容器和Spring MVC

## Spring Boot的Web开发支持

Spring Boot提供了spring-boot-starter-web为web开发予以支持，spring-boot-starter-web为我们提供了潜入之Tomcat以及Spring MVC的依赖。而Web相关的自动配置存储在spring-boot-autoconfigure.jar的org.springframework.boot.web下:

![](https://oscimg.oschina.net/oscnet/89a2ea9cb223db9e7e8e364fe6c0262532e.jpg)


在这些文件中有如下作用：

- EmbeddedWebServerFactoryCustomizerAutoConfiguration和ServerProperties配置用于嵌入式servlet和响应式web服务器的自定义

- ServletWebServerFactoryAutoConfiguration和ServerProperties配置用于配置Servlet容器服务器.

- HttpEncodingAutoConfiguration和HttpProperties用来自动配置编码.

- MultipartAutoConfiguration和MultipartProperties用来配置文件上传的属性.

- WebMvcAutoConfiguration和WebMvcProperties配置SpringMVC


## Thymeleaf模板引擎

内嵌的Servlet容器不支持JSP的运行，除此之外Undertow不支持jsp,Spring支持大量的模板引擎，包括FreeMark,Groovy,Thymeleaf，Velocity和Mustache,Spring Boot中推荐使用Thymeleaf模板引擎，因为Thymeleaf提供了完美的SpringMVC支持.

### Thymeleaf的基础知识

Thymeleaf时一个Java类库，他是xml/xhtml/html5的模板引擎，可以作为MVC的web应用的View层。Thymeleaf还提供了额外的模块与Spring MVC集成，所有完全可以替代了JSP。

例子：

	<html xmlns:th="http://www.thymeleaf.org">
	<head>
		<meta content="text/html;charset=UTF-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
		//根据设备自适应
		<meta name="viewport" content="width=device-width, initial-scale=1"/>
		//引入静态资源
		<link th:href="@{bootstrap/css/bootstrap.min.css}" rel="stylesheet"/>
		<link th:href="@{bootstrap/css/bootstrap-theme.min.css}" rel="stylesheet"/>
	</head>
	<body>

	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">访问model</h3>
		</div>
		<div class="panel-body">
			<span th:text="${singlePerson.name}"></span>
		</div>
	</div>

	<div th:if="${not #lists.isEmpty(people)}">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">列表</h3>
			</div>
			<div class="panel-body">
				<ul class="list-group">
					<li class="list-group-item" th:each="person:${people}">
						<span th:text="${person.name}"></span>
						<span th:text="${person.age}"></span>
						<button class="btn" th:onclick="'getName(\'' + ${person.name} + '\');'">获得名字</button>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<script th:src="@{jquery-1.10.2.min.js}" type="text/javascript"></script><!-- 2 -->
	<script th:src="@{bootstrap/js/bootstrap.min.js}"></script><!-- 2 -->

	<script th:inline="javascript">
		var single = [[${singlePerson}]];
		console.log(single.name + "/" + single.age)
		function getName(name) {
			console.log(name);
		}
	</script>
	</body>
	</html>



- 通过xmlns：th=http://www.thymeleaf.org命名空间，将镜头页面转换为动态的视图。需要进行动态处理的元素将使用“th：”为前缀；


- 通过@{}引用Web静态资源，这个JSP下时极易出错。


访问model中的数据
通过${}访问model中的属性，这和JSP极为相似。

		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">访问model</h3>
			</div>
			<div class="panel-body">
				//使用<span th:text="${singlePerson.name}"/>访问model中的singlePerson的name手续ing。
				//需要处理的动态内容需要加上th:前缀。
				<span th:text="${singlePerson.name}"></span>
			</div>
		</div>



model中的数据迭代

Thymeleaf中的数据迭代和JSP的写法很相似。

		<div th:if="${not #lists.isEmpty(people)}">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">列表</h3>
				</div>
				<div class="panel-body">
					<ul class="list-group">
					//th:each来做循环迭代，th:each="person:${people}"person作为迭代元素使用
						<li class="list-group-item" th:each="person:${people}">
							<span th:text="${person.name}"></span>
							<span th:text="${person.age}"></span>
							<button class="btn" th:onclick="'getName(\'' + ${person.name} + '\');'">获得名字</button>
						</li>
					</ul>
				</div>
			</div>
		</div>


数据判断
通过${not#lists.isEmpty(people)}表达式判断people是否为空。Thymeleaf支持>,<,>=,<===,!=作为比较条件，同时也支持SpEL表达式

	<div th:if="${not #lists.isEmpty(people)}">

在JavaScript中访问model

在项目中，我们需要在JavaScript访问model中的值，在Thymeleaf里的代码如下:

		<script th:inline="javascript">
			var single = [[${singlePerson}]];
			console.log(single.name + "/" + single.age)

			function getName(name) {
				console.log(name);
			}
		</script>

- 通过th:inline="javascript"添加到script标签，这样JavaScript代码即可访问model中的属性。

- 通过[[${}]]格式获取实际的值。


## SpringBoot的Thymeleaf支持

SpringBoot通过org.springframework.boot.autoconfigure.thymeleaf包对Thymeleaf进行了自动配置i
![](https://oscimg.oschina.net/oscnet/6f6c7221386f6fd503d8b2765d1d20edea3.jpg)

		@ConfigurationProperties(prefix = "spring.thymeleaf")
		public class ThymeleafProperties {

			private static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;

			public static final String DEFAULT_PREFIX = "classpath:/templates/";

			public static final String DEFAULT_SUFFIX = ".html";

			/**
			 * Whether to check that the template exists before rendering it.
			 */
			private boolean checkTemplate = true;

			/**
			 * Whether to check that the templates location exists.
			 */
			private boolean checkTemplateLocation = true;

			/**
			 * Prefix that gets prepended to view names when building a URL.
			 */
			private String prefix = DEFAULT_PREFIX;

			/**
			 * Suffix that gets appended to view names when building a URL.
			 */
			private String suffix = DEFAULT_SUFFIX;

			/**
			 * Template mode to be applied to templates. See also Thymeleaf's TemplateMode enum.
			 */
			private String mode = "HTML";

			/**
			 * Template files encoding.
			 */
			private Charset encoding = DEFAULT_ENCODING;

			/**
			 * Whether to enable template caching.
			 */
			private boolean cache = true;

			/**
			 * Order of the template resolver in the chain. By default, the template resolver is
			 * first in the chain. Order start at 1 and should only be set if you have defined
			 * additional "TemplateResolver" beans.
			 */
			private Integer templateResolverOrder;

			/**
			 * Comma-separated list of view names (patterns allowed) that can be resolved.
			 */
			private String[] viewNames;

			/**
			 * Comma-separated list of view names (patterns allowed) that should be excluded from
			 * resolution.
			 */
			private String[] excludedViewNames;

			/**
			 * Enable the SpringEL compiler in SpringEL expressions.
			 */
			private boolean enableSpringElCompiler;

			/**
			 * Whether hidden form inputs acting as markers for checkboxes should be rendered
			 * before the checkbox element itself.
			 */
			private boolean renderHiddenMarkersBeforeCheckboxes = false;

			/**
			 * Whether to enable Thymeleaf view resolution for Web frameworks.
			 */
			private boolean enabled = true;

在application.yml或者application.properties使用spring.thymeleaf对Thymeleaf进行配置.

例子：https://github.com/chenanddom/SpringWebSupport



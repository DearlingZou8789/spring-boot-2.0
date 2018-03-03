项目是按照慕课网上的Spring Boot 2.0的课程项目，Spring Boot 2.0深度实践-初遇Spring Boot。 采用多模块化组合。 在学习中发现一个POST请求问题。 /** * 保护用户 * @param olduser 用户名 * @return 返回保存的用户 * @consult * 1、如果依赖spring-boot-starter-web，RequestParam可以正常使用 * 2、但是如果依赖spring-boot-starter-webflux，就会一直报Required String parameter 'name' is not present * * 总结： * 1、spring-boot-starter-web, 采用@RequestParam就可以通过paw中From URL-Encoded * 2、spring-boot-starter-webflux,采用@RequestBody,同时跟Struts2中一样通过模型传递参数 */ @PostMapping(value = "/person/save") public User save(@RequestBody User olduser){ User user = new User(); user.setName(olduser.getName()); if (userRepository.save(user)){ System.out.print("用户保存成功"); } return user; }

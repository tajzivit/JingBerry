package net.tajzich.raspberry.demo.app

import groovy.transform.CompileStatic
import org.eclipse.jetty.server.Handler
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.HandlerList
import org.eclipse.jetty.server.handler.ResourceHandler
import org.eclipse.jetty.servlet.FilterHolder
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder
import org.eclipse.jetty.servlets.CrossOriginFilter
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.filter.HiddenHttpMethodFilter
import org.springframework.web.servlet.DispatcherServlet

import javax.servlet.DispatcherType

@CompileStatic
class Main {

    public static void main(String[] args) throws Exception {

        def appDir = new File('')

        def angularDir = new File('angular')

        println "angular: " + angularDir.absolutePath

        Server server = new Server(8080);

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setWelcomeFiles(['index.html'].toArray(new String[1]));
        resourceHandler.setResourceBase(angularDir.absolutePath);

        FilterHolder hiddenMethod = new FilterHolder(HiddenHttpMethodFilter)
        hiddenMethod.name = 'HiddenHttpMethodFilter'

        FilterHolder crossOriginFilter = new FilterHolder(CrossOriginFilter)
        crossOriginFilter.name = 'cross-origin'
        crossOriginFilter.initParameters = ['allowedOrigins': '*',
                                            'allowedMethods': 'GET,HEAD,POST,PUT,PATCH,DELETE,OPTIONS,TRACE',
                                            'allowedHeaders': 'accept,origin,content-type'
        ]

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.addEventListener(new ContextLoaderListener())
        context.setInitParameter('contextConfigLocation', 'classpath*:applicationContext.xml')
        context.setResourceBase(new File(appDir, 'webapp').absolutePath)

        EnumSet dispatches = EnumSet.copyOf([DispatcherType.REQUEST])

        context.addFilter(hiddenMethod, '/rest/*', dispatches)
        context.addFilter(crossOriginFilter, '/*', dispatches)

        ServletHolder holder = new ServletHolder(DispatcherServlet)
        holder.setName('rest')
        holder.setInitParameter('contextConfigLocation', 'classpath:rest-servlet.xml')

        context.addServlet(holder, '/*')

        HandlerList handlers = new HandlerList(handlers: (Handler[]) [resourceHandler, context].toArray());

        server.setHandler(handlers);

        server.start();
        server.join();
    }
}

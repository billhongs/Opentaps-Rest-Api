In order to enable logging of wink you should copy the files:

rest-api/lib/dep/slf4j-log4j12-1.6.2.jar
rest-api/lib/dep/slf4j-api-1.6.1.jar

in the framework/base/lib directory and add the following to the log4j.xml file

    <category name="org.apache.wink">
        <priority value="all"/>
    </category>


 if you would like to log the rest-api messages into a separate file add this:

    <appender name="wink-log" class="org.apache.log4j.RollingFileAppender">
        <param name="maxFileSize" value="5000KB"/>
        <param name="maxBackupIndex" value="10"/>
        <param name="File" value="runtime/logs/wink.log"/>
        <param name="Append" value="false"/>
        <layout class="org.apache.log4j.PatternLayout">
            <param name="ConversionPattern" value="%d (%t) [%24F:%-3L:%-5p]%x %m%n"/>
        </layout>
    </appender>

        <category name="org.apache.wink">
          <priority value="all"/>
        <appender-ref ref="wink-log"/>
    </category>



################  SPRING SECURITY ###################
in order for spring security to work you should remove the library spring-core.jar in framework/base/lib/etl/spring
the rest api module should me loaded in the component-load.xml before all other modules
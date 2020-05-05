# докер файл только для томкета, для мускула нет (зато есть докер компоуз файл)
FROM tomcat:9.0-jre8-slim
ENV app_name parts.list.war
COPY target/${app_name} webapps/${app_name}
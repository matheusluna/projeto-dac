FROM payara/server-full
ENV APPNAME rhecruta
ENV DOMAIN domain1
ENV AUTODEPLOY ${PAYARA_PATH}/glassfish/domains/${DOMAIN}/autodeploy/
ENTRYPOINT $PAYARA_PATH/bin/asadmin start-domain --verbose ${DOMAIN}
COPY target/${APPNAME}.war  ${AUTODEPLOY}

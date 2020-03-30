FROM jboss/wildfly:8.2.1.Final

# Appserver
ENV WILDFLY_USER admin
ENV WILDFLY_PASS dina

# Expose http and admin ports
EXPOSE 8080 8443 9990

RUN mkdir -p /opt/jboss/wildfly/excel
RUN /opt/jboss/wildfly/bin/add-user.sh admin dina --silent

RUN mkdir -p /opt/jboss/wildfly/loan /opt/jboss/wildfly/loan_policy
COPY ./files/Loanpolicycommon.pdf /opt/jboss/wildfly/loan_policy/Loanpolicycommon.pdf
COPY ./files/Loanpolicyforscientificpurposes.pdf /opt/jboss/wildfly/loan_policy/Loanpolicyforscientificpurposes.pdf

ADD mysql-connector-java-5.1.48.jar /opt/jboss/wildfly/standalone/deployments/

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

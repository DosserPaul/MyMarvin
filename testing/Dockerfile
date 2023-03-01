FROM jenkins/jenkins:lts

ENV JAVA_OPTS "-Djenkins.install.runSetupWizard=false -Dpermissive-script-security.enabled=true"
COPY tester-plugins.dat /home/plugins.dat
RUN /usr/local/bin/install-plugins.sh < /home/plugins.dat



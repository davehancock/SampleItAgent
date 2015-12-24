FROM centos:centos6

# Enable Extra Packages for Enterprise Linux (EPEL) for CentOS
RUN yum install -y epel-release

# Install Java 8
RUN yum install -y java-1.8.0-openjdk

# Copy Binary
COPY ./build/libs/SampleItServer.jar /app/dist/SampleItAgent.jar
WORKDIR /app/dist

CMD ["java", "-jar", "SampleItAgent.jar"]
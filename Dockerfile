FROM apache/spark:3.5.7

# Copy the fat JAR we just built into Spark's work directory
COPY build/libs/*-all.jar /opt/spark/work-dir/app.jar
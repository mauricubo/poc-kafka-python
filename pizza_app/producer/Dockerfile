# Use an official Python runtime as a base image
FROM python:3.8

# Set the working directory to /app
WORKDIR /app

# Install the required dependencies
RUN pip install Faker kafka-python

# Copy the current directory contents into the container at /app
COPY ./*.py /app

# Run main.py when the container launches
CMD ["python", "main.py"]
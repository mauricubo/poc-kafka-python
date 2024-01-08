# Use an official Python runtime as a base image
FROM python:3.8

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . ./

# Install the required dependencies
RUN pip install -r requirements.txt

# Run main.py when the container launches
CMD ["python", "main.py"]
FROM python:3.10-slim
WORKDIR /app
COPY requirements.txt requirements.txt
COPY gateway.py gateway.py
COPY model.py model.py
RUN pip install --no-cache-dir -r requirements.txt
EXPOSE 8000
CMD ["uvicorn", "gateway:app", "--host", "0.0.0.0", "--port", "8000"]

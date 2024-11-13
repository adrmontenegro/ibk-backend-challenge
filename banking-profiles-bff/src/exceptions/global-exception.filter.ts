import { ArgumentsHost, Catch, ExceptionFilter, HttpException, HttpStatus } from "@nestjs/common";
import { Request, Response } from 'express';
import { AxiosError } from 'axios';

interface ErrorResponse {
  code: string;
  message: string;
}

@Catch()
export class GlobalExceptionFilter implements ExceptionFilter {
  catch(exception: unknown, host: ArgumentsHost) {
    const ctx = host.switchToHttp();
    const response = ctx.getResponse<Response>();

    let status = HttpStatus.INTERNAL_SERVER_ERROR;
    let errorResponse: ErrorResponse = {
      code: 'INTERNAL_SERVER_ERROR',
      message: 'Internal server error'
    };

    if (exception instanceof HttpException) {
      status = exception.getStatus();
      
      errorResponse = {
        code: 'HTTP_EXCEPTION',
        message: exception.message,
      };
    } else if (exception instanceof AxiosError && exception.response) {
      status = exception.response.status || HttpStatus.INTERNAL_SERVER_ERROR;
      const data = exception.response.data as { code: string; message: string };

      errorResponse = {
        code: data.code || 'EXTERNAL_SERVICE_ERROR',
        message: data.message || exception.message || 'Error from external service',
      };
    }

    response.status(status).json(errorResponse);
  }
}

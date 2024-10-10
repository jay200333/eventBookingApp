package com.example.eventbookingapp.config.interceptors

import com.example.eventbookingapp.config.logger
import okhttp3.Interceptor
import okhttp3.Response

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        logger.debug {
            "-------------------------------------------------------------\n"
        }

        logger.debug {
            "Request URL\n${request.url}\n\n"
        }

        logger.debug {
            "Request Body\n${request.body}\n\n"
        }

        val response = chain.proceed(request)

        logger.debug {
            "Response body about ${request.url}\n${response.body}\n\n"
        }

        logger.debug {
            "-----------------------------END----------------------------\n"
        }

        return response
    }

}
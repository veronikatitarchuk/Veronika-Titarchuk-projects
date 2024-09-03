library(astsa)
library(forecast)
library(tseries)
library(PerformanceAnalytics)
library(xts)
library(ggplot2)
library(fGarch)
library(rugarch)
library(zoo)
library(arfima)
library(fracdiff)

mpw <- read.csv("C:/Users/User/Desktop/Study/Time Series/MPW.csv")
ts_mpw <- ts(mpw$Adj.Close, start=c(2005,08), frequency=12)

#Exploratory Data Analysis
ts.plot(ts_mpw, gpars=list(xlab="Date", ylab="Adjusted Closed"))
?ts.plot
plot(decompose(ts_mpw))
acf2(ts_mpw)
adf.test(ts_mpw)

ts.plot(diff(ts_mpw), gpars=list(xlab="Date", ylab="Differenced Adjusted Closed"))

acf2(diff(ts_mpw))
adf.test(diff(ts_mpw))

#just messing around
y <- log(max(mpw$Adj.Close)-mpw$Adj.Close+1)

ts_trial <- ts(y, start=c(2005,08), frequency=12)
plot(decompose(ts_trial))
ts.plot(diff(ts_trial))
acf(diff(ts_trial))
garch_trial <- garchFit(~garch(1,1), diff(ts_trial))
#finish messing around

#model fitting
#Arima
auto.arima(ts_mpw)


get.best.arima <- function(x.ts, maxord = c(1,1,1,1,1,1))
{
  best.aic <- 1e8
  n <- length(x.ts)
  for (p in 0:maxord[1]) for(d in 0:maxord[2]) for(q in 0:maxord[3])
    for (P in 0:maxord[4]) for(D in 0:maxord[5]) for(Q in 0:maxord[6])
    {
      fit <- arima(x.ts, order = c(p,d,q),
                   seas = list(order = c(P,D,Q),
                               frequency(x.ts)), method = "CSS")
      fit.aic <- -2 * fit$loglik + (log(n) + 1) * length(fit$coef)
      if (fit.aic < best.aic)
      {
        best.aic <- fit.aic
        best.fit <- fit
        best.model <- c(p,d,q,P,D,Q)
      }
    }
  list(best.aic, best.fit, best.model)
}


arima.fit <- get.best.arima(ts_mpw,
                            maxord=c(2,2,2,2,2,2))

mpw_arima <- sarima(ts_mpw, p = 0, d = 1, q = 0, P = 0, D = 1, Q = 1, S = 12)
sarima_residuals <- mpw_arima$fit$residuals
adf.test(sarima_residuals)

#garch
garch_spec <- ugarchspec(variance.model = list(model = "sGARCH", garchOrder = c(1, 1)),
                         mean.model = list(armaOrder = c(0, 0), include.mean = FALSE),
                         distribution.model = "std")
garch_fit <- ugarchfit(data = sarima_residuals, spec = garch_spec)
adf.test(garch_fit@fit$residuals)
ts.plot(garch_fit@fit$residuals)

#Forecast arima and garch

garch_forecast <- ugarchforecast(garch_fit, n.ahead = 24)
sarima_forecast <- sarima.for(ts_mpw, 24, 0,1,0,0,1,1,12)

combined_forecast <- garch_forecast@forecast$seriesFor + sarima_forecast$pred
print(combined_forecast)





# Assuming you have combined_forecast containing the combined forecasts
# Create a time index for the forecasted periods
forecast_dates <- seq(length(combined_forecast))

forecast_data <- data.frame(Date = as.yearmon(time(combined_forecast)), Forecast = combined_forecast)

# Plot the combined forecasts
ggplot(forecast_data, aes(x = Date, y = Apr.2024)) +
  geom_line(color = "blue") +
  labs(x = "Time", y = "Forecasted Value", title = "Combined GARCH and SARIMA Forecasts") +
  theme_minimal()



plot(ts_mpw, main = "Medical Properties Trust", xlab = "Date", ylab = "Adjusted Closed", col = "blue", xlim = c(2005, 2026.5))

# Add lines for forecasted values
lines(as.yearmon(time(combined_forecast)), combined_forecast, col = "red")








#Train data (test of the model)

train_data <- ts(mpw$Adj.Close, start=c(2005,08), end = c(2023,09), frequency=12)
sarima_train_data <- sarima(ts_mpw, p = 0, d = 1, q = 0, P = 0, D = 1, Q = 1, S = 12)
sarima_train_residuals <- sarima_train_data$fit$residuals

garch_fit_train <- ugarchfit(data = sarima_train_residuals, spec = garch_spec)

garch_forecast_train <- ugarchforecast(garch_fit_train, n.ahead = 10)
sarima_forecast_train <- sarima.for(train_data, 10, 0,1,0,0,1,1,12)

combined_forecast_train <- garch_forecast_train@forecast$seriesFor + sarima_forecast_train$pred

plot(ts_mpw, main = "Time Series Plot", xlab = "Date", ylab = "Value", col = "blue")
lines(as.yearmon(time(combined_forecast_train)), combined_forecast_train, col = "red")


#Neural Network

(fit <- nnetar(ts_mpw, lambda=0.5))
adf.test(na.omit(fit$residuals))
fcast <- forecast(fit, h=24)
autoplot(fcast)

#train data
(fit_train <- nnetar(train_data, lambda=0.5))
adf.test(na.omit(fit_train$residuals))
fcast_train <- forecast(fit_train, h=24)
autoplot(fcast_train)
ts.plot(fcast_train$mean)

plot(ts_mpw, main = "Time Series Plot", xlab = "Date", ylab = "Value", col = "blue")
lines(as.yearmon(time(fcast_train$mean)), fcast_train$mean, col = "red")

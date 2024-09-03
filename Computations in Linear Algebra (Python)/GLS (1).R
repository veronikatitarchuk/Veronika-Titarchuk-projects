
library(readxl)

# Read the .xls file
Apple_data = read_excel("/Users/mingyuhuang/Desktop/Time series/project_data.xls",sheet=2)


# OLS model
OLS=lm(Apple_data$Adj_Close~Apple_data$Apple_Open+Apple_data$`Apple-Close`+Apple_data$`Apple-Volume`)
summary(OLS)
residuals <- resid(OLS)
plot(residuals,main="OLS residuals vs Order")

plot(open,X%*%OLS$coefficients)

dwtest(OLS) # autocorrelation exist significantly

############ GLS
# creating X matrix, ginv() means generalized inverse, solve() means normal inverse
open=Apple_data$Apple_Open
close=Apple_data$`Apple-Close`
volume=Apple_data$`Apple-Volume`
y=Apple_data$Adj_Close


X=cbind(rep(1,length(open)),open,close,volume)
X=as.matrix(X)
XX=t(X)%*%X
library(Matrix)
# rankMatrix(XX)

library(MASS)
H=X%*%ginv(XX)%*%t(X)
# H[1:5,1:5]
# H%*%H-H
# H-t(H)
V=diag(nrow=nrow(H),ncol=ncol(H))-H
# V[1:5,1:5]
# eigen(V)$values

#compute K s.t. KK'=V

################erase this before submit
# chelosky suck suck suck!!!!!!!!! smd!
# it cannot handle the rounding error,
# it fucking deny my symetric matrix, saying not symetric
# stupid!!!!!!

#use eigenvalue/vector compute K

eigen_decomp <- eigen(V)

eigenvalues <- eigen_decomp$values
eigenvectors <- eigen_decomp$vectors

sqrt_eigenvalues <- sqrt(eigenvalues)
sqrt_eigenvalues[is.na(sqrt_eigenvalues)] <- 0


D <- diag(sqrt_eigenvalues)

K <- eigenvectors %*% D %*% t(eigenvectors)
# (K%*%t(K)-V)[1:4,1:4] #double check

kinv=ginv(K)
# kinv[1:4,1:4]


########
# GLS
new_beta=solve(t(X)%*%ginv(V)%*%X)%*%t(X)%*%ginv(V)%*%y
new_beta

#-------------------
new_residuals=X%*%new_beta-y
new_residuals=scale(new_residuals)
plot(new_residuals, main="GLS residuals vs Order")

# Function to calculate Durbin-Watson test statistic
durbin_watson <- function(residuals) {
  num <- sum(diff(residuals)^2)  
  denom <- sum(residuals^2)       
  d <- num/denom    
  return(d)
}

d <- durbin_watson(new_residuals)
d
# p-value
2*pf(d,2,length(y)-1)
# 0.708, fail to reject H0. no significant autocorrelation


# plot GLS Model
fitted=X%*%new_beta

plot(open,kinv%*%y,ylab="Adjusted Close Price", xlab="Open Price")
abline(line(open,fitted),col="red")

# Problem 1
# Beta Distribution a=2, B=10
# i) a)
set.seed(2811)
B <- rbeta(200, 2, 10)
# b)
hist(B)
# c)
x_vals <- seq(0, 1, length.out = 1000)
pdf_vals <- dbeta(x_vals, 2, 10)
lines(x_vals, pdf_vals, col = "red")
# d)
qqnorm(B)
qqline(B, col = 'blue')
# e)
conditional_B <- B[B < 0.3]
cat('Empirical conditional expectation:', mean(conditional_B), '\n')

# ii) a)
print(1 - pbeta(0.7, 2, 10))
print(mean(B > 0.7))
# b)
print(IQR(B))
# c) Var(aX + b) = a^2Var(X)
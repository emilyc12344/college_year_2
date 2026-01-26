# The probability of having a male child can be assumed to be 0.51 independently from birth to birth.
# i)
    # a) from scratch
    # P(X = k) = (1 - p)^k * p
    prob = ((1 - 0.51) ** 3) * (0.51)
    print(prob)
    # b) using the dgeom function
    print(dgeom(3, 0.51)) # x = 3 (fails before success), prob = 0.51 (prob of success)
# ii) 
probs <- c()
for (x in 0:10) {
    p = ((1 - 0.51) ** x) * (0.51)
    probs <- append(probs, p)
}
barplot(probs, xlab='probability', ylab='number of daughters', main='Daughters before 1st son')
# iii)
    # a) at most 4 daughters before her first son
print(pgeom(4, 0.51))
    # b) more than 2 daughters before her first son
print(1 - pgeom(2, 0.51))
    # c) one daughter before her first son.
print((1 - 0.51) * (0.51))

# iv) Draw a stepped graph of the CDF using the plot function.
x = 0:10
y = c()
for (i in x) {
  y <- append(y, pgeom(i, 0.51))
}
plot(x, y, type='s', xlab='number of daughters', ylab='CDF')
# v) Use qgeom to calculate the smallest number of daughters, x , before the first son such that:
    # a) P(X ≤ x) ≥ 0.9
print(qgeom(0.9, 0.51)) # P(X <= x) >= prob gives number you will not exceed before success
    # b) P(X > x) ≤ 0.4
print(qgeom(0.6, 0.51))

# vi) Use set.seed(47) and rgeom to simulate the number of daughters before the first son 1,000 times. Store this in the object G.
set.seed(47)
G <- rgeom(1000, 0.51)
# vii) Using G from (vi):
    # a) Use length to obtain the empirical probabilities for part (iii) and comment.
print(length(G[G >= 4]) / 1000)  
# b) Use quantile to calculate the empirical results for part (v) and comment.

# viii) Compare the following statistics for the distribution and simulated values(G) :
    # a) mean
    # b) variance
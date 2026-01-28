################################################################################
#
# MTH1082 Statistics II lab w3
# 27 January 2026
#
################################################################################

#------------------------------------------------------------------
print('Proportion test (binomial)')
# (normal approximation & exact binom.test)
#------------------------------------------------------------------

# In a one-year mortality investigation, 45 of the 250 90-year-olds
# present at the start of the investigation died before the end of the yr.  
# Test if probability of death is 0.2.

x = 45
n = 250
p_hat = x / n
alpha = 0.05
p_hat+c(-1, 1)*qnorm(1-alpha/2)*sqrt(p_hat*(1-p_hat)/n)

binom.test(x, n, conf.level=1-alpha)

#------------------------------------------------------------------
print('')
print('One sample t-test')
#------------------------------------------------------------------
# Test whether the mean weight of a turtle is 310 using the following sample
# 300, 315, 320, 311, 314, 309, 300, 308, 305, 303, 305, 301, 303, 312, 298

t = c(300, 315, 320, 311, 314, 309, 300, 308, 305, 303, 305, 301, 303, 312, 298)
mean(t)

t1 = t.test(t, mu=310, alternative='less', conf.level=0.9)
t1$p.value

#------------------------------------------------------------------
print('')
print('Two sample t-test (KNOWN variances)')
#------------------------------------------------------------------

# Average IQ of a sample of 50 students from university A was found  to be 132. 
# For a sample of 40 students from university B it was 128
# The sd of the IQs from the two universities were 20 and 25.
# Calculate a symmetrical 95% CI for the difference in mean IQs

n1 = 50
n2 = 40
mu1 = 132
mu2 = 128
sd1 = 20
sd2 = 25
alpha = 0.05

(mu1 - mu2) + c(-1, 1) * qnorm(1 - alpha/2) * sqrt((sd1^2)/n1 + (sd2^2)/n2)

#------------------------------------------------------------------
print('')
print('Two sample tests (UNKNOWN variances)')
#------------------------------------------------------------------

# A motor company runs tests to investigate the fuel consumption of cars
# using a newly developed fuel additive.
# Sixteen cars of the same make and age are used,
# eight with the new additive and eight as controls.
# The results, in mpg over a test track, are as follows:

#Control	27.0   32.2   30.4   28.0   26.5   25.5   29.6   27.2
#Additive	31.4   29.9   33.2   34.4   32.0   28.7   26.1   30.3

C = c(27.0, 32.2, 30.4, 28.0, 26.5, 25.5, 29.6, 27.2)
A = c(31.4, 29.9, 33.2, 34.4, 32.0, 28.7, 26.1, 30.3)
nC = nA = 8
alpha = 0.05

# CASE 1: means, assuming equal vars

SP = ((nC-1)*var(C)+(nA-1)*var(A))/(nA+nC-2)
mean(A)-mean(C)+c(1, -1)*qnorm(1-alpha/2)*sqrt(SP)*sqrt(1/nA+1/nC)

t.test(A, C, var.equal=TRUE)

# CASE 2: means, assuming NOT equal vars

t.test(A, C, var.equal=FALSE)

# CASE 3: means, PAIRED

t.test(A, C, paired=TRUE)

# CASE 4: ratio of vars (F-test)

r = var(C) / var(A)
int_high = r * qf(1-alpha/2, nA-1, nC-1)
int_high = r * qf(alpha/2, nA-1, nC-1)
var.test(C, A)

#------------------------------------------------------------------
print('')
print('Built-in dataset')
#------------------------------------------------------------------

# Use 'chickwts' dataset
# find 90% CI for ratio of variances

attach(chickwts)
soy = chickwts[feed=="soybean",1]
casein = chickwts[feed=="casein",1]
var.test(soy, casein)
detach(chickwts)

#------------------------------------------------------------------
print('')
print('Goodness of fit test - given freq/prob')
#------------------------------------------------------------------

# A study of favourite touristic destinations has been conducted in 2015
# among visitors of Guardian webpage. A similar study has also been conducted 
# 10 years earlier. Test whether the new results are substantially different.

# Favourite destination      # 2005 results            # 2015 results
# Portugal                   8%                        286
# Italy                      22%                       805
# Spain                      40%                       1,548
# Greece                     19%                       755
# Other                      11%                       464

historical = c(0.08, 0.22, 0.4, 0.19, 0.11)
updated = c(286, 805, 1548, 755, 464)

O = updated
E = historical * sum(updated)

statistic = sum((O-E)^2/E)
pval = 1-pchisq(statistic, 4)
pval
# not reject at 5% level of significance

chisq.test(updated, p=historical)

#------------------------------------------------------------------
print('')
print('Goodness of fit test - fitting a distribution')
#------------------------------------------------------------------

# The #claims made last year by indiv motor ins. policyholders were:

# Number of claims          0     1     2     3     4+
# Number of policyholders 2,962  382   47    25     4

# Carry out a test to determine whether these frequencies
# can be considered to conform to a Poisson distribution.

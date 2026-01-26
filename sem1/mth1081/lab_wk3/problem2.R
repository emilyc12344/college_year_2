# tells how many successes in 4 trails

uniform_values <- c(0.588, 0.222, 0.906)

for (x in uniform_values) {
  print(qbinom(x, 4, 0.6))
}
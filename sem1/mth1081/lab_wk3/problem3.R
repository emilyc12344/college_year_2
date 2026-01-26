# Define the probability distribution
n_values <- 0:3
probabilities <- c(0.55, 0.25, 0.15, 0.05)

# Calculate cumulative probabilities
cumulative_probs <- cumsum(probabilities)

# Function to simulate N given uniform r
simulate_N <- function(r) {
  if (r <= cumulative_probs[1]) return(0)
  else if (r <= cumulative_probs[2]) return(1)
  else if (r <= cumulative_probs[3]) return(2)
  else return(3)
}

print(simulate_N(0.6221))
print(simulate_N(0.1472))
print(simulate_N(0.9862))
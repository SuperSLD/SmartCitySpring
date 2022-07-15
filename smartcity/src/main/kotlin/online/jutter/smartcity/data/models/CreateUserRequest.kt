package online.jutter.smartcity.data.models

data class CreateUserRequest(
   val name: String,
   val lastname: String,
   val city: String,
   val phone: String,
   val avatar: String,
)

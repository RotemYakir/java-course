class User{
    constructor(id,email,password,firstName,lastName,userName,role){
        this.id=id;
        this.email=email;
        this.password=password;
        this.firstName=firstName;
        this.lastName=lastName;
        this.userName=userName;
        this.role=role;
    }
}

class UserCredentials {
    constructor(email, password) {
      this.email = email;
      this.password = password;
    }
  }
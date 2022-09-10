class Blog {
    constructor(text, title) {
        this.text = text;
        this.title = title;
    }
}

class User {
    constructor(username, password) {
        this.username = username;
        this.password = password;
    }
}

class Account {
    constructor(user, name, tel, address, remarks, email) {
        this.user = user;
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.remarks = remarks;
        this.email = email;
    }
}
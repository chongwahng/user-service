namespace ppg.user;

entity Users {
    key ID        : String(12);
        firstName : String(40);
        lastName  : String(40);
        fullName  : String(80);
}

using ppg.user from '../db/data-model';

service UserService {
    @readonly
    entity Users as projection on user.Users;
}

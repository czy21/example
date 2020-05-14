export class UserModel { 
    constructor(
        public nickname?: string,
        public headimgurl?: string,
        public UserId?: number,
        public refresh_token?:string
    ) { }
}
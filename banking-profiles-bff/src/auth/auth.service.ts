import { Injectable } from '@nestjs/common';
import { JwtService } from '@nestjs/jwt';
import { compare, genSalt, hash } from 'bcryptjs';
import { UserService } from '../user/user.service';


@Injectable()
export class AuthService {
  constructor(
    private usersService: UserService,
    private jwtService: JwtService
  ) { }

  async validateUser(username: string, password: string): Promise<any> {
    const user = await this.usersService.findUserByUsername(username);
    if (user) {
      const passwordMatches = await compare(password, user.password);
      if (passwordMatches) {
        const { password, ...result } = user;
        return result;
      }
    }
    return null;
  }

  async loginUser(user: any) {
    const payload = { username: user.username, sub: user.id };
    return {
      access_token: this.jwtService.sign(payload),
    };
  }

  async registerUser(user: any) {
    const salt = await genSalt(10);
    const hashedPassword = await hash(user.password, salt);
    return this.usersService.createUser(
      user.username,
      hashedPassword,
      salt
    );
  }
}

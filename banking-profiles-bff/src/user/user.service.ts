import { Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose';
import { UserDocument } from './user.schema';

@Injectable()
export class UserService {
  constructor(@InjectModel('User') private userModel: Model<UserDocument>) {}

  async createUser(username: string, password: string, salt: string): Promise<UserDocument> {
    const newUser = await this.userModel.create({ username, password, salt }); 
    return newUser;
  }

  async findUserByUsername(username: string): Promise<UserDocument | null> {
    const user = this.userModel.findOne({ username }).exec();
    return user;
  }
}

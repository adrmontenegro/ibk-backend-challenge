import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { BankingProfileModule } from './banking-profile/banking-profile.module';
import { AuthModule } from './auth/auth.module';
import { UserModule } from './user/user.module';
import { MongooseModule } from '@nestjs/mongoose';


@Module({
  imports: [
  MongooseModule.forRoot(process.env.MONGO_URI),
    AuthModule, UserModule,
    BankingProfileModule,],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule { }

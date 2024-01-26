package skillbox.com.users;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import skillbox.com.users.entity.CityEntity;
import skillbox.com.users.entity.SubscriptionEntity;
import skillbox.com.users.entity.UserEntity;
import skillbox.com.users.repository.CityRepository;
import skillbox.com.users.repository.SubscriptionRepository;
import skillbox.com.users.repository.UserRepository;

import java.util.Optional;

@SpringBootApplication
public class UsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersApplication.class, args);
    }

    @Bean
    CommandLineRunner demoJpa(CityRepository cityRepository,
                              UserRepository userRepository,
                              SubscriptionRepository subscriptionRepository) {
        return (args) -> {


            CityEntity moscow = new CityEntity("Moscow");
            CityEntity himki = new CityEntity("Himki");
            CityEntity sochi = new CityEntity("Sochi");

            try {
                cityRepository.save(moscow);
                cityRepository.save(himki);
                cityRepository.save(sochi);
            } catch (Exception e) {
                System.err.println(e);
            }


            System.out.println("List of Cities");
            cityRepository.findAll().forEach(System.out::println);


            Optional<CityEntity> optionalCityEntity = cityRepository.findById(3);
            if (optionalCityEntity.isPresent()) {
                System.out.println("City = " + optionalCityEntity.get());
            }
            int count = 1;
            for (CityEntity city : cityRepository.findAll()) {
                try {
                    UserEntity user1 = new UserEntity();
                    user1.setName("Пользователь" + count);
                    user1.setLogin("USER" + count);
                    user1.setGender("M"); // Male
                    user1.setEmail("user" + count + "@gmail.com");
                    user1.setPhone("8(495)111-11-1" + count);
                    //user1.setActive(true);
                    user1.setAddress("Какой-то адрес, дом " + count);
                    user1.setCityId(city.getId());
                    userRepository.save(user1);
                } catch (Exception e) {
                    System.err.println(e);
                }
				finally {
					count++;
				}
            }

            System.out.println("List of Users");
            userRepository.findAll().forEach(System.out::println);
            //
            userRepository.deleteById(3);

            long countSub = userRepository.count();
            for (UserEntity user : userRepository.findAll()) {
                try {
                    SubscriptionEntity subs = new SubscriptionEntity();
                    subs.setSubscribedId(user.getId());
                    subs.setSubscriberId((int) countSub);
                    //subs.setSubscribeDate();
                    subscriptionRepository.save(subs);
                } catch (Exception e) {
                    System.err.println(e);
                }
            }

            System.out.println("List of Subscriptions");
            subscriptionRepository.findAll().forEach(System.out::println);

        };
    }
}

package org.example.spotfy.Repository;

import org.example.spotfy.Models.SpotUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSpotRepository extends JpaRepository<SpotUser, Long> {

    Boolean existsByNickName(String nickName);
}

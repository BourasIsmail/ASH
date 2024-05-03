package ma.entraide.ash.repository;

import ma.entraide.ash.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> ,
        PagingAndSortingRepository<UserInfo, Integer> {

    Optional<UserInfo> findByName(String name);

    Optional<UserInfo> findByEmail(String email);



}

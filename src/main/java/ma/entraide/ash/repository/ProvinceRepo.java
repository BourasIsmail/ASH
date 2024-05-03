package ma.entraide.ash.repository;

import ma.entraide.ash.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepo extends JpaRepository<Province, Long> ,
        PagingAndSortingRepository<Province, Long> {
}

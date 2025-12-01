/*package com.kubraevren.mapper;
import com.kubraevren.dto.ExpenseCreateDto;
import com.kubraevren.dto.ExpenseDto;
import com.kubraevren.model.ExpenseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    // ExpenseEntity'den ExpenseDto'ya dönüşüm
    ExpenseDto toDto(ExpenseEntity entity);

    // ExpenseCreateDto'dan ExpenseEntity'ye dönüşüm
    // ID, User ve CreatedAt otomatik oluştuğu için map'lemiyoruz
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    ExpenseEntity toEntity(ExpenseCreateDto dto);

    // Listelerin dönüşümü için
    List<ExpenseDto> toDtoList(List<ExpenseEntity> entities);
}


 */


package com.kubraevren.mapper;

import com.kubraevren.dto.ExpenseCreateDto;
import com.kubraevren.dto.ExpenseDto;
import com.kubraevren.model.ExpenseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component // BU NOTASYON ÇOK ÖNEMLİ: Spring'in bunu görmesini sağlar.
public class ExpenseMapper {

    // Entity -> DTO
    public ExpenseDto toDto(ExpenseEntity entity) {
        if (entity == null) return null;

        ExpenseDto dto = new ExpenseDto();
        dto.setId(entity.getId());
        dto.setAmount(entity.getAmount());
        dto.setCategory(entity.getCategory());
        dto.setTransactionType(entity.getTransactionType());
        dto.setDescription(entity.getDescription());
        dto.setDate(entity.getDate());
        return dto;
    }

    // DTO -> Entity
    public ExpenseEntity toEntity(ExpenseCreateDto dto) {
        if (dto == null) return null;

        ExpenseEntity entity = new ExpenseEntity();
        entity.setAmount(dto.getAmount());
        entity.setCategory(dto.getCategory());
        entity.setDescription(dto.getDescription());
        entity.setDate(dto.getDate());
        entity.setTransactionType(dto.getTransactionType());
        // User ve ID service katmanında set edilecek, burası boş kalabilir
        return entity;
    }

    // Liste Çevirimi
    public List<ExpenseDto> toDtoList(List<ExpenseEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
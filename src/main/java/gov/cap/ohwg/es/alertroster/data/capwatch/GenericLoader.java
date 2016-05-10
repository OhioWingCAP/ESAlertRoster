package gov.cap.ohwg.es.alertroster.data.capwatch;

import gov.cap.ohwg.es.alertroster.model.entity.Identifiable;
import gov.cap.ohwg.es.alertroster.model.entity.Unit;
import gov.cap.ohwg.es.alertroster.model.repo.GenericRepo;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by ckovacs on 5/10/16.
 */
public class GenericLoader<T extends Identifiable> {

    private final GenericRepo<T> repo;
    private final String[] headers;
    private final String resourcePath;
    private final Class targetClass;

    public GenericLoader(GenericRepo<T> repo, String[] headers, String resourcePath, Class<T> targetClass) {
        this.repo = repo;
        this.headers = headers;
        this.resourcePath = resourcePath;
        this.targetClass = targetClass;
    }

    public void load() {
        repo.deleteAll();
        FlatFileItemReader<T> reader = new FlatFileItemReader<>();
        reader.setLinesToSkip(1);
        reader.setResource(new ClassPathResource(resourcePath));
        DefaultLineMapper<T> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(headers);
        lineMapper.setLineTokenizer(tokenizer);
        BeanWrapperFieldSetMapper<T> mapper = new BeanWrapperFieldSetMapper<>();
        mapper.setTargetType(targetClass);
        lineMapper.setFieldSetMapper(mapper);
        reader.setLineMapper(lineMapper);
        reader.open(new ExecutionContext());
        T target = null;
        try {
            while ((target = reader.read()) != null) {
                repo.save(target);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

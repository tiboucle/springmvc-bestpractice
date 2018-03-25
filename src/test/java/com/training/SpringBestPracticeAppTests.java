package com.training;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.training.model.dao.NewsRepository;
import com.training.model.entity.News;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBestPracticeAppTests {

	@Autowired
	private NewsRepository newsRepository;

	@Test
	public void save_and_retieve_news() {
		News news = newsRepository.save(new News("TEST2", "TEST2", new Date(), "AJAT"));
		News foundNews = newsRepository.findById(news.getId()).orElse(null);

		assertNotNull(foundNews);
		assertEquals(news.getTitle(), foundNews.getTitle());
	}

}

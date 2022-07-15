package com.example.demo;

import com.example.demo.generated.GetReviewRequest;
import com.example.demo.generated.ReviewResponseProjection;
import com.example.demo.generated.ReviewsQueryRequest;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequest;
import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequestSerializer;
import java.time.LocalDate;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		GraphQLRequestSerializer.OBJECT_MAPPER.registerModule(new SimpleModule().addSerializer(new DateSerializer()));
		var query =
				ReviewsQueryRequest.builder().setSearchInput(GetReviewRequest.builder().setSubmittedDate(LocalDate.now()).build()).build();
		var req = new GraphQLRequest(query, new ReviewResponseProjection().starScore());

		System.out.println(req.toQueryString());
	}

}

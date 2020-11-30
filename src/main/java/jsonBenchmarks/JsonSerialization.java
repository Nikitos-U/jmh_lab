package jsonBenchmarks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class JsonSerialization {

        private static final JsonDto dto = new JsonDto();
        private static final ObjectMapper objectMapper = new ObjectMapper();


        @Benchmark
        @BenchmarkMode(Mode.Throughput)
        public void serializationThroughput() throws JsonProcessingException {
            String result = objectMapper.writeValueAsString(dto);
        }

        @Benchmark
        @BenchmarkMode(Mode.AverageTime)
        public void serializationAvgTime() throws JsonProcessingException {
            String result = objectMapper.writeValueAsString(dto);
        }


        public static void main(String[] args) throws RunnerException {
            Options opt = new OptionsBuilder()
                    .include(JsonSerialization.class.getSimpleName())
                    .threads(4)
                    .warmupIterations(5)
                    .measurementIterations(7)
                    .forks(1)
                    .build();

            new Runner(opt).run();
        }
}

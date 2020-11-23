import {
  Link as ChakraLink,
  Text,
  Code,
  Icon,
  List,
  ListIcon,
  ListItem,
  Box,
  Image,
  Flex,
} from "@chakra-ui/core";
import axios from "axios";
import { useEffect, useState } from "react";
import { useInfiniteQuery } from "react-query";
import { useQuery } from "react-query";
import { Navbar } from "../components/Navbar";

const Index = () => {
  const { data, isLoading, error, isError } = useQuery(
    "fetchItems",
    async () => {
      return await axios.get("http://localhost:8080/api/items");
    }
  );

  useEffect(() => {}, [data]);

  if (isLoading) {
    return <p>...Loading</p>;
  }

  if (isError) {
    return (
      <>
        <p>There was an error loading items, please try again</p>
        <p>{error}</p>
      </>
    );
  }

  return (
    <>
      <Navbar />
      <Flex m={25} flexDir="row">
        {data.data.map((item) => {
          return (
            <Box
              maxW="sm"
              borderWidth="1px"
              borderRadius="lg"
              overflow="hidden"
              key={item.itemId}
              alignItems="center"
            >
              <Image margin="auto" mt={15} src={item.imageUri} />

              <Box
                mt="1"
                fontWeight="semibold"
                as="h4"
                lineHeight="tight"
                isTruncated
                m="1"
              >
                {item.name}
              </Box>
              <Box m="1">
                <Text isTruncated>{item.description}</Text>
                <Text>${item.price}</Text>
              </Box>
            </Box>
          );
        })}
      </Flex>
    </>
  );
};

export default Index;

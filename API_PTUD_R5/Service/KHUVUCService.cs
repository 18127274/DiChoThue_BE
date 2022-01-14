using API_PTUD_R5.Model;
using MongoDB.Driver;
using System.Collections.Generic;
using System.Linq;

namespace API_PTUD_R5.Service
{
    public class KHUVUCService
    {
        private readonly IMongoCollection<KHUVUC> _books;

        // <snippet_BookServiceConstructor>
        public KHUVUCService(IDatabaseSettings settings)
        {
            var client = new MongoClient(settings.ConnectionString);
            var database = client.GetDatabase(settings.DatabaseName);
            _books = database.GetCollection<KHUVUC>(settings.KhuVucCollectionName);
        }
        // </snippet_BookServiceConstructor>

        public List<KHUVUC> Get() =>
            _books.Find(book => true).ToList();

        public KHUVUC Get(long id) =>
            _books.Find<KHUVUC>(book => book.Id == id).FirstOrDefault();

        public KHUVUC Create(KHUVUC book)
        {
            book.Id = _books.AsQueryable().Count() + 1;
            _books.InsertOne(book);
            return book;
        }

        public void Update(long id, KHUVUC bookIn) =>
            _books.ReplaceOne(book => book.Id == id, bookIn);

        public void Remove(KHUVUC bookIn) =>
            _books.DeleteOne(book => book.Id == bookIn.Id);

        public void Remove(long id) =>
            _books.DeleteOne(book => book.Id == id);
    }
}
// </snippet_BookServiceClass>

